import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { KorisnikUsluga } from 'src/app/models/korisnikUsluga';
import { Usluga } from 'src/app/models/usluga';
import { KorisnikUslugaService } from 'src/app/services/korisnikUsluga.service';
import { UslugaService } from 'src/app/services/usluga.service';

@Component({
  selector: 'app-usluga-dialog',
  templateUrl: './usluga-dialog.component.html',
  styleUrls: ['./usluga-dialog.component.css']
})
export class UslugaDialogComponent {
  korisnici!: KorisnikUsluga[];
  public flag!: number;
  korisnikUslugaSubscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<UslugaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Usluga,
    public uslugaService: UslugaService,
    public korisnikUslugaService: KorisnikUslugaService) { }

  ngOnDestroy(): void {
    this.korisnikUslugaSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.korisnikUslugaSubscription = this.korisnikUslugaService.getAllKorisnikUsluga()
      .subscribe(korisnici => {
        this.korisnici = this.korisnici;
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }

  compareTo(a: any, b: any) {
    return a.id === b.id;
  }

  public add(): void {
    this.uslugaService.addUsluga(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno dodata usluga filijale!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public update(): void {
    this.uslugaService.updateUsluga(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno modifikovana usluga filijale!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public delete(): void {
    this.uslugaService.deleteUsluga(this.data.id)
      .subscribe(() => {
        this.snackBar.open('Uspešno obrisana usluga filijale!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'Zatvori', {
      duration: 1500
    })
  }
}

