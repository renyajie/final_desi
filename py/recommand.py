# -*- coding: utf-8 -*-

# 获取指定用户编号的团课推荐列表
def getPeopleRecommandList(userId):
    return list(range(userId, userId + 2))

# 获取指定用户编号的私教推荐列表
def getIndividualRecommandList(userId):
    return list(range(userId, userId + 3))