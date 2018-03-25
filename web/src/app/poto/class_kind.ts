/**
 * 课程种类
 */
export class ClassKind {
    constructor(
        public id?: number,
        public pId?: number,
        public pName?: string,
        public property?: string,
        public claKName?: string,
        public difficulty?: number,
        public intro?: string
    ) {}

    static fromJSON(json: any): ClassKind {
        const classKind = Object.create(ClassKind.prototype);
        Object.assign(classKind, json);
        return classKind;
    }

    toString() {
        return `
        ClassKind {
            id: ${this.id},
            pId: ${this.pId},
            pName: ${this.pName},
            property: ${this.property},
            claKName: ${this.claKName},
            difficulty: ${this.difficulty},
            intro: ${this.intro}
        }`;
    }
}