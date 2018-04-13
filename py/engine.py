# -*- coding: utf-8 -*-
from math import sqrt

class recommender:
    '''
    # data：数据集，这里指users
    # k：表示得出最相近的k的近邻
    # metric：表示使用计算相似度的方法
    # n：表示推荐book的个数
    '''
    def __init__(self, data, userIdToName, classKindIdToName, k = 10, n = 5):

        self.k = k
        self.n = n
        self.userIdToName = userIdToName
        self.classKindIdToName = classKindIdToName
        self.data = data
      
    def convertClassKindIdToName(self, id):
        '''
        将课程种类编号转换为课程名
        '''
        if id in self.classKindIdToName:
            return self.classKindIdToName[id]
        else:
            return id
    
    def convertUserIdToName(self, id):
        '''
        将用户编号转化为用户名
        '''
        if id in self.userIdToName:
            return self.userIdToName[id]
        else:
            return id
    

    def pearson(self, rating1, rating2):
        '''
        计算pearson相关性系数
        '''
        sum_xy = 0
        sum_x = 0
        sum_y = 0
        sum_x2 = 0
        sum_y2 = 0
        n = 0
        for key in rating1:
            if key in rating2:
                n += 1
                x = rating1[key][2]
                y = rating2[key][2]
                sum_xy += x * y
                sum_x += x
                sum_y += y
                sum_x2 += pow(x, 2)
                sum_y2 += pow(y, 2)
        if n == 0:
            return 0
        
        # 皮尔逊相关系数计算公式 
        denominator = sqrt(sum_x2 - pow(sum_x, 2) / n)  * sqrt(sum_y2 - pow(sum_y, 2) / n)
        if denominator == 0:
            return 0
        else:
            return (sum_xy - (sum_x * sum_y) / n) / denominator
    
    
    def computeNearestNeighbor(self, userId):
        '''
        计算某个用户与其他用户之间的相似度，生成距离列表，格式为
        [
            (用户名A, 距离1), 
            (用户名B, 距离2)
            ...
        ]
        '''

        distances = []
        for instance in self.data:
            if instance != userId:
                distance = self.pearson(self.data[userId], self.data[instance])
                distances.append((instance, distance))
        
        #升序排序，将距离近的邻居放在上方
        distances.sort(key=lambda artistTuple: artistTuple[1], reverse=True)
        return distances
    
    
    def recommend(self, userId):
        '''
        推荐算法的主体函数
        '''

        # 定义一个字典，用来存储推荐的课程和分数
        recommendations = {}
        #计算出user与所有其他用户的相似度，返回一个list
        nearest = self.computeNearestNeighbor(userId)
        # print nearest
        userRatings = self.data[userId]

        totalDistance = 0.0
        #最近的k个近邻的总距离
        for i in range(self.k):
            totalDistance += nearest[i][1]
        if totalDistance == 0.0:
            totalDistance = 1.0
            
        #将与user最相近的k个人中user没有上过的课推荐给user，并且这里又做了一个分数的计算排名
        for i in range(self.k):
            
            #第i个人的与user的相似度，转换到[0, 1]之间，表示权重
            weight = nearest[i][1] / totalDistance
            
            #第i个邻居用户
            userI = nearest[i][0]

            #第i个用户上的课和评分
            neighborRatings = self.data[userI]

            for classKind in neighborRatings:
                # 找出当前用户没有接触过的课
                if not classKind in userRatings:
                    # 如果当前课程不在推荐列表中加入，有则累加
                    if classKind not in recommendations:
                        recommendations[classKind] = neighborRatings[classKind][2] * weight
                    else:
                        recommendations[classKind] = recommendations[classKind] + neighborRatings[classKind][2] * weight
        
        # 把map中的数据对转化为list，将(课程种类编号, 分数)转化为(课程名, 分数)数据对
        recommendations = list(recommendations.items())
        #recommendations = [(self.convertClassKindIdToName(k), v) for (k, v) in recommendations]

        # nearest中的用户编号转化为用户名称
        #nearest = [(self.convertUserIdToName(k), v) for (k, v) in nearest]

        #根据分数对数据对进行降序排序
        recommendations.sort(key=lambda artistTuple: artistTuple[1], reverse = True)

        # 返回前n个推荐结果，以及和用户的邻居列表
        return recommendations[:self.n], nearest