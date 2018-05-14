package data;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoExample;
import com.ryj.yuyue.bean.CardInfoExample.Criteria;
import com.ryj.yuyue.bean.CardOrder;
import com.ryj.yuyue.bean.ClassInfo;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.dao.CardInfoMapper;
import com.ryj.yuyue.dao.CardOrderMapper;
import com.ryj.yuyue.dao.ClassInfoMapper;
import com.ryj.yuyue.dao.ClassOrderMapper;

/**
 * 创建用户约课订单，动态的创建用户购卡订单和会员卡种类订单
 * @author Thor
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CreateClassOrder {

	
	@Autowired
	private CardInfoMapper cardInfoMapper;
	
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	@Autowired
	private CardOrderMapper cardOrderMapper;
	
	Calendar calendar = new GregorianCalendar();
	Random rand = new Random();
	
	int[] timeDistance = {-1, -2, -3};
	
	/**
	 * 判断某用户是否拥有，某种会员卡，有则返回对应的会员卡编号，没有则购买该种会员卡，并返回编号
	 * @param userId 用户编号
	 * @param cardKindId 会员卡种类编号
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public int getCardInfoId(int userId, int cardKindId, Date time) {
		CardInfoExample example = new CardInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(userId);
		criteria.andCardKIdEqualTo(cardKindId);
		List<CardInfo> result = cardInfoMapper.selectByExample(example);
		
		//若存在，更新会员卡余额，并返回编号，若不存在，则创建会员卡订单和会员卡记录后，返回编号
		if(result.size() != 0) {
			CardInfo cardInfo = result.get(0);
			cardInfo.setAllowance(cardInfo.getAllowance() - 1);
			cardInfoMapper.updateByPrimaryKeySelective(cardInfo);
			return cardInfo.getId();
		}
		
		//创建会员卡记录
		CardInfo cardInfo = new CardInfo();
		cardInfo.setuId(userId);
		cardInfo.setCardKId(cardKindId);
		//使用了一次来预约
		cardInfo.setAllowance(99);
		cardInfoMapper.insertSelective(cardInfo);
		
		//创建会员卡订单
		Date orderTime = time;
		orderTime.setMinutes(time.getMinutes() - 1);
		
		CardOrder cardOrder = new CardOrder();
		cardOrder.setCardId(cardInfo.getId());
		cardOrder.setuId(userId);
		cardOrder.setCardKId(cardKindId);
		cardOrder.setOrdTime(orderTime);
		cardOrderMapper.insertSelective(cardOrder);
				
		return cardInfo.getId();
	}
	
	/**
	 * 返回某门课程的预约时间为上课时间的前[1-3]天，小时[7-17]，分钟和秒使用随机数生成
	 * @param classId 课程编号
	 * @return
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	public Date getOrderTime(Integer classId) {
		ClassInfo classInfo = classInfoMapper.selectByPrimaryKey(classId);
		Date cDay = classInfo.getcDay();
		calendar.setTime(cDay);
	    calendar.add(calendar.DATE, timeDistance[rand.nextInt(3)]);
	    Date date = calendar.getTime();
	    date.setHours(rand.nextInt(11) + 7);
	    date.setMinutes(rand.nextInt(60));
	    date.setSeconds(rand.nextInt(60));
	    return date;
	}
	
	/**
	 * 每个用户从1200次团课选择10-15次上，从1200次私教课中选择10-15次上
	 * 若用户没有该瑜伽馆的会员卡，用户开卡，若瑜伽馆没有会员卡，瑜伽馆开卡后，用户再购买卡，然后下单
	 */
	@Test
	public void addClassOrder() {
		
		ClassOrder classOrder = null;
		ClassInfo classInfo = null;
		
		//上课次数， 哪个瑜伽馆，哪个课，哪种课，会员卡编号
		int time, placeId, classId, cardId;
		//下单时间
		Date orderTime = null;
		
		//每个用户的上课场馆，每个用户选择2-3个场馆上课
		Set<Integer> placeSets = new HashSet<Integer>();
		
		for(int i = 0; i < 1000; i++) {
			
			//生成用户上课场馆，每个用户选择[2,3]个瑜伽馆上课
			placeSets.clear();
			int number = rand.nextInt(2) + 2;
			while(placeSets.size() < number) {
				placeSets.add(rand.nextInt(20) + 1);
			}
			Integer[] placeList = new Integer[number];
			placeList = placeSets.toArray(placeList);
			
			//循环两次，团课一次，私教一次
			for(int m = 0; m < 2; m++) {
				
				int j = 0;
				
				//用户上课次数[10,15]
				time = rand.nextInt(6) + 10;
				
				while(j < time) {
					
					placeId = placeList[rand.nextInt(number)];
					classId = (placeId - 1) * 60 + rand.nextInt(60) + 1 + m * 1200;
					
					//若用户预约课程已经没有余量则重新选择课程，若还有余额则更新课程安排
					classInfo = classInfoMapper.selectByPrimaryKey(classId);
					if(classInfo.getAllowance() == 0) {
						continue;
					}
					classInfo.setAllowance(classInfo.getAllowance() - 1);
					classInfo.setOrderNum(classInfo.getOrderNum() + 1);
					classInfoMapper.updateByPrimaryKeySelective(classInfo);
					
					orderTime = getOrderTime(classId);
					/**
					 * 获取瑜伽馆会员卡种类编号， 查看用户有无该种会员卡
					 *    1. 有，获取会员卡编号，下订单
					 *    2. 无，买会员卡，获取会员卡编号，下订单
					 */
					cardId = getCardInfoId(i + 1, placeId, orderTime);
					
					classOrder = new ClassOrder();
					classOrder.setIsScore(1);
					classOrder.setuId(i + 1);
					classOrder.setOrdTime(orderTime);
					classOrder.setNum(1);
					classOrder.setExpend(1);
					classOrder.setClaId(classId);
					classOrder.setCardId(cardId);
					classOrderMapper.insertSelective(classOrder);
					
					j++;
				}
			}
			
		}
	}
}
