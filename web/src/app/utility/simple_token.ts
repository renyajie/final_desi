export class SimpleToken {
    constructor(public label: string, public value: string|number) {};

    static fromJSON(json: any): SimpleToken {
        const simpleToken = Object.create(SimpleToken.prototype);
        Object.assign(simpleToken, json);
        return simpleToken;
    }
}