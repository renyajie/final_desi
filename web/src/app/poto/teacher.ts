/**
 * 教师
 */
export class Teacher {
    constructor(
        public id?: number,
        public pId?: number,
        public pName?: string,
        public teaName?: string,
        public phone?: string,
        public intro?: string
    ) {}

    static fromJSON(json: any): Teacher {
        const teacher = Object.create(Teacher.prototype);
        Object.assign(teacher, json);
        return teacher;
    }
}