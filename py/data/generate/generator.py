# -*- coding: utf-8 -*-
'''
这个文件负责生成评论数据
'''

import random

first_name_file = r'E:\python-workspace\final_desi\py\data\generate\first_name'
last_name_file = r'E:\python-workspace\final_desi\py\data\generate\last_name'

def getFirstName():
    '''
    获取姓氏
    '''
    name = []
    number = 0
    for line in open(first_name_file, encoding='utf-8'):
        lines = line.strip().split(' ')
        for character in lines:
            name.append(character)
            number = number + 1
    
    return name, number

def getLastName():
    '''
    获取姓名
    '''
    name = []
    number = 0
    for line in open(last_name_file, encoding='utf-8'):
        lines = line.strip().split(' ')
        #若为空行则跳过
        if len(lines) == 1:
            continue
        
        for character in lines:
            name.append(character)
            number = number + 1
    
    return name, number

def generateUserName(number):
    '''
    生成用户姓名， 入参表示姓名数量
    '''
    firstNameList, firstNameNumber = getFirstName()
    lastNameList, lastNameNumber = getLastName()

    nameList = []
    for i in range(0, number):
        newName = firstNameList[random.randint(0, firstNameNumber - 1)] \
            + lastNameList[random.randint(0, lastNameNumber - 1)]
        nameList.append(newName)
    
    return nameList

if __name__ == '__main__':
    print(generateUserName(100))

