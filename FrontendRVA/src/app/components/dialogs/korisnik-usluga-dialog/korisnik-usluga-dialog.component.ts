import { Component, Inject } from '@angular/core';
import { KorisnikUsluga } from 'src/app/models/korisnikUsluga';
import { KorisnikUslugaService } from 'src/app/services/korisnikUsluga.service';
import { KorisnikUslugaComponent } from '../../korisnik-usluga/korisnik-usluga.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-korisnik-usluga-dialog',
  templateUrl: './korisnik-usluga-dialog.component.html',
  styleUrls: ['./korisnik-usluga-dialog.component.css']
})
export class KorisnikUslugaDialogComponent {

  public flagArtDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public korisnikUslugaService: KorisnikUslugaService,
    @Inject(MAT_DIALOG_DATA) public dataKorisnikUsluga: KorisnikUsluga,
    public dialogRef: MatDialogRef<KorisnikUslugaComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataKorisnikUsluga.id + this.dataKorisnikUsluga.ime);
    this.korisnikUslugaService.addKorisnikUsluga(this.dataKorisnikUsluga).subscribe(() => {
      this.snackBar.open('Uspesno dodat korisnik: ' + this.dataKorisnikUsluga.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.korisnikUslugaService.updateKorisnikUsluga(this.dataKorisnikUsluga).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen korisnik: ' + this.dataKorisnikUsluga.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene artikla. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.korisnikUslugaService.deleteKorisnikUsluga(this.dataKorisnikUsluga.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisan korisnik: ' + this.dataKorisnikUsluga.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}
