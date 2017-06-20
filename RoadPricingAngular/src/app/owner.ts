/**
 * Created by Nekkyou on 17-6-2017.
 */
export class Owner {
    constructor(
      public name?: string ,
      public address?: string,
      public residence?: string,
      public username?: string,
      public password?: string,
      public canEditPrice?: boolean,
      public usesWebsite?: boolean
    ) {
    }
}
