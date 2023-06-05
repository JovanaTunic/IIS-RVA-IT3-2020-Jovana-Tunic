import { Banka } from "./banka";

export class Filijala {
    id!: number;
    adresa!: string;
    brojPultova!:number;
    posedujeSef!: boolean;
    banka!: Banka;  // ceo objekat - nije kljuc, jer baza radi sa stranim kljucevima, a ovde je to objekat
}
