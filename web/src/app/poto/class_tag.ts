/**
 * 课程标签
 */
export class ClassTag {
    constructor(
        public id?: number,
        public claKId?: number,
        public claKName?: string,
        public property?: string,
        public difficulty?: number,
        public relaxed?: number,
        public intense?: number,
        public common?: number,
        public recovery?: number,
        public enhance?: number,
        public nurse?: number,
        public consume?: number
    ) {}

    static fromJSON(json: any): ClassTag {
        const classTag = Object.create(ClassTag.prototype);
        Object.assign(classTag, json);
        return classTag;
    }
}