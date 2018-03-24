/**
 * 瑜伽场馆
 */
export class Place {
    constructor(
        public id?: number,
        public phone?: string,
        public sName?: string,
        public address?: string
    ) {}

    static fromJSON(json: any): Place {
        const place = Object.create(Place.prototype);
        Object.assign(place, json);
        return place;
    }

    toString() {
        return `
        Place {
            id: ${this.id},
            phone: ${this.phone},
            sName: ${this.sName},
            address: ${this.address}
        }`;
    }
}