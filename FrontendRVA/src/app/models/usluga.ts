import { Filijala } from "./filijala";
import { KorisnikUsluga } from "./korisnikUsluga";

export class Usluga {
    id!: number;
    datum_ugovora!: Date;
    naziv!: string;
    opis_usluge!: string;
    provizija!: number;
    korisnik_usluga!: KorisnikUsluga;
    filijala!: Filijala;
}
