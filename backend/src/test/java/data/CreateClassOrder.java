package data;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ryj.yuyue.bean.CardInfo;
import com.ryj.yuyue.bean.CardInfoExample;
import com.ryj.yuyue.bean.CardInfoExample.Criteria;
import com.ryj.yuyue.bean.ClassOrder;
import com.ryj.yuyue.dao.CardInfoMapper;
import com.ryj.yuyue.dao.CardKindMapper;
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
	private CardKindMapper cardKindMapper;
	
	@Autowired
	private CardInfoMapper cardInfoMapper;
	
	@Autowired
	private ClassOrderMapper classOrderMapper;
	
	@Autowired
	private ClassInfoMapper classInfoMapper;
	
	Random rand = new Random();
	
	/**
	 * 判断某用户是否拥有，某种会员卡，有则返回对应的会员卡编号，没有则购买该种会员卡，并返回编号
	 * @param userId 用户编号
	 * @param cardKindId 会员卡种类编号
	 * @return
	 */
	public int getCardInfoId(int userId, int cardKindId) {
		CardInfoExample example = new CardInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUIdEqualTo(userId);
		criteria.andCardKIdEqualTo(cardKindId);
		List<CardInfo> result = cardInfoMapper.selectByExample(example);
		
		//若存在则返回编号，若不存在，则创建返回编号
		if(result.size() != 0) {
			return result.get(0).getId();
		}
		
		CardInfo cardInfo = new CardInfo();
		cardInfo.setuId(userId);
		cardInfo.setCardKId(cardKindId);
		cardInfo.setAllowance(100);
		cardInfoMapper.insertSelective(cardInfo);
		return cardInfo.getId();
	}
	
	/**
	 * 每个用户从800次团课选择10-15次上，从800次私教课中选择10-15次上
	 * 若用户没有该瑜伽馆的会员卡，用户开卡，若瑜伽馆没有会员卡，瑜伽馆开卡后，用户再购买卡，然后下单
	 */
	@Test
	public void addClassOrder() {
		
		ClassOrder classOrder = null;
		
		//上课次数， 哪个瑜伽馆，哪个课，哪种课，会员卡编号
		int time, placeId, classId, classKId, cardId;
		
		for(int i = 0; i < 1000; i++) {
			
			time = rand.nextInt(6) + 10;
			
			for(int j = 0; j < time; j++) {
				
				placeId = rand.nextInt(20) + 1;
				classId = rand.nextInt(800) + 1 + 800;
				/**
				 * 获取瑜伽馆会员卡种类编号， 查看用户有无该种会员卡
				 *    1. 有，获取会员卡编号，下订单
				 *    2. 无，买会员卡，获取会员卡编号，下订单
				 */
				cardId = getCardInfoId(i + 1, placeId);
				
				classOrder = new ClassOrder();
				classOrder.setIsScore(0);
				classOrder.setuId(i + 1);
				classOrder.setOrdTime(new Date());
				classOrder.setNum(1);
				classOrder.setExpend(1);
				classOrder.setClaId(classId);
				classOrder.setCardId(cardId);
				classOrderMapper.insertSelective(classOrder);
			}
		}
	}
}
