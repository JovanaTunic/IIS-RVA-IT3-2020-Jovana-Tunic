import { Filijala } from "./filijala";
import { KorisnikUsluga } from "./korisnikUsluga";

export class Usluga {
    id!: number;
    datumUgovora!: Date;
    naziv!: string;
    opisUsluge!: string;
    provizija!: number;
    korisnikUsluga!: KorisnikUsluga;
    filijala!: Filijala;
}
