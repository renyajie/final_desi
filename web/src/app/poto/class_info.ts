/**
 * 课程信息
 */
export class ClassInfo {
    constructor(
        public id?: number,
        public claKId?: number,
        public claKName?: string,
        public pId?: number,
        public pName?: string,
        public teaId?: number,
        public teaName?: string,
        public cDay?: string,
        public staTime?: string,
        public endTime?: string,
        public length?: string,
        public allowance?: string,
        public orderNum?: string,
        public expend?: string,
        public property?: string
    ) {}

    static fromJSON(json: any): ClassInfo {
        const classInfo = Object.create(ClassInfo.prototype);
        Object.assign(classInfo, json);
        return classInfo;
    }
}