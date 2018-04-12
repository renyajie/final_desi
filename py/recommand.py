# -*- coding: utf-8 -*-
from math import sqrt
from engine import recommender

peopleFile = r'E:\python-workspace\final_desi\py\data\people_class_score_record'
individualFile = r'E:\python-workspace\final_desi\py\data\individual_class_score_record'

'''
数据集格式
{
    {
        '用户编号1': 
        {
            '课程种类编号1': ('总分', '次数'， '平均分'),
            '课程种类编号2': ('总分', '次数'， '平均分'),
            '课程种类编号3': ('总分', '次数'， '平均分')
            ...
        },
        '用户编号2': 
        {
            '课程种类编号1': ('总分', '次数'， '平均分'),
            '课程种类编号2': ('总分', '次数'， '平均分'),
            '课程种类编号3': ('总分', '次数'， '平均分')
            ...
        },
    }
}
'''

# 读取用户评论数据数据，若要读去团课评论传入true, 若要读取私教评论传入false
def readScoreRecord(isPeople):
    # python 中的三目元表达式: x if x > y else y 一个求最大值的例子
    file = peopleFile if isPeople else individualFile
    users = {}
    userIdToName = {}
    classKindIdToName = {}

    for line in open(file, encoding="utf-8"):
        lines = line.strip().split(",")
        # 若历遍到新用户则加入users中
        if lines[0] not in users:
            users[lines[0]] = {}
        # 将每一行的信息记录到对应用户数据集中
        if lines[2] in users[lines[0]]:
            total = users[lines[0]][lines[2]][0] + float(lines[4])
            num = users[lines[0]][lines[2]][1] + 1
            average = total / num
            users[lines[0]][lines[2]] = total, num, average
        else:
            users[lines[0]][lines[2]] = float(lines[4]), 1, float(lines[4])
        
        #拼装用户Id到姓名的映射
        if lines[0] not in userIdToName:
            userIdToName[lines[0]] = lines[1]
        #拼装课程种类Id到课程名称的映射
        if lines[2] not in classKindIdToName:
            classKindIdToName[lines[2]] = lines[3]
    return users, userIdToName, classKindIdToName

# 获取某个用户编号的课程种类推荐
def recommend(id):
    class_kind_id_list = []
    r = recommender(users)
    k, nearuser = r.recommend("%s" % id)
    for i in range(len(k)):
        class_kind_id_list.append(k[i][0])
    # class_kind_id_list推荐课程种类的id，nearuser[:15]最近邻的15个用户
    return class_kind_id_list, nearuser[:15]

def getPeopleRecommandList(userId):
    '''
    获取指定用户编号的团课推荐列表
    '''
    userScoreSet = readScoreRecord(True)

    classKId_list = []
    r = recommender(userId)
    k, nearuser = r.recommend("%s" % id)

    for i in range(len(k)):
        classKId_list.append(k[i][0])
    
    return bookid_list, nearuser[:15]
    return 1

def getIndividualRecommandList(userId):
    '''
    获取指定用户编号的私教推荐列表
    '''
    return 1
'''
if __name__ == '__main__':
    print(readScoreRecord(True))
    print(readScoreRecord(False))
'''