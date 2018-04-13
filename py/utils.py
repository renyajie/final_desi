# -*- coding: utf-8 -*-
import random


'''
这个文件负责写入用户评论记录，包括私教评论和团课评论两种
'''

peopleClassFileLocation = r'E:\python-workspace\final_desi\py\data\people_class_score_record'
individualClassFileLocation = r'E:\python-workspace\final_desi\py\data\individual_class_score_record'

def addPeopleClassScoreRecore(userId, userName, classKindId, classKindName, score):
    '''
    将团课评价记录写入到文件中
    数据格式为: 用户编号, 用户名, 课程种类编号, 课程种类名称, 评分
    '''
    file = open(peopleClassFileLocation, 'a+', encoding="utf-8")
    file.write(
        '{userId}, {userName}, {classKindId}, {classKindName}, {score}\n'
        .format(
            userId = userId, userName = userName, classKindId = classKindId, 
            classKindName = classKindName, score = score));
    file.close()

def addIndividualClassScoreRecore(userId, userName, classKindId, classKindName, score):
    '''
    将私教评价记录写入到文件中
    数据格式为: 用户编号, 用户名, 课程种类编号, 课程种类名称, 评分
    '''
    file = open(individualClassFileLocation, 'a+', encoding="utf-8")
    file.write(
        '{userId}, {userName}, {classKindId}, {classKindName}, {score}\n'
        .format(
            userId = userId, userName = userName, classKindId = classKindId, 
            classKindName = classKindName, score = score))
    file.close()

'''
if __name__ == '__main__':
    addPeopleClassScoreRecore(1, "任亚捷", 2, "2", 2.0)

'''
