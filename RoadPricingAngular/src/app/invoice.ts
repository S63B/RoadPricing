import { User } from "app/user";

export class Invoice {
    constructor(
        public id: number,
        public owner: User,
        public date: Date,
        public totalPrice: number,
        public startDate: Date,
        public endDate: Date,
        public paymentStatus: number,
        public countryOfOrigin: string
    ) {

    }

    get getDownloadURL(): string {
        return `http://localhost:8080/pdf?user=${this.owner.id}&start_date=${this.startDate.getMilliseconds}&end_date=${this.endDate.getMilliseconds}`;
    }

}
