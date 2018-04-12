/**
 * 用户
 */
export class User {
    constructor(
        public id?: number,
        public phone?: string,
        public passwd?: string,
        public uName?: string,
        public gender?: string,
        public age?: number
    ) {}

    static fromJSON(json: any): User {
        const user = Object.create(User.prototype);
        Object.assign(user, json);
        return user;
    }

    toString() {
        return `
        User {
            id: ${this.id},
            phone: ${this.phone},
            passwd: ${this.passwd},
            uName: ${this.uName},
            gender: ${this.gender},
            age: ${this.age}
        }`;
    }
}