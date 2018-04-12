# -*- coding: utf-8 -*-

print("hello")

def adder(a, b):
    return [a, b]

# 将文件记录写入到python文件中
# 数据格式为: 用户编号, 用户名, 课程种类编号, 课程种类名称, 评分
fileLocation = r'E:\python-workspace\final_desi\py\score_record'
def addScoreRecore(userId, userName, classKindId, classKindName, score):
    with open(fileLocation, 'a+', encoding="utf-8") as file:
        file.write(
            '{userId}, {userName}, {classKindId}, {classKindName}, {score}\n'
            .format(
                userId = userId, userName = userName, classKindId = classKindId, 
                classKindName = classKindName, score = score));

addScoreRecore(1, "任亚捷", 1, "基础瑜伽", 3.0)
addScoreRecore(1, "任亚捷", 2, "初级瑜伽", 3.0)
addScoreRecore(2, "冰糖", 2, "高级瑜伽", 4.0)

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

users = {}
def readScoreRecord():
    for line in open(fileLocation, encoding="utf-8"):
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
    print(users)

readScoreRecord()
