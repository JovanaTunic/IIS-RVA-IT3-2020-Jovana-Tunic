import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { Filijala } from 'src/app/models/filijala';
import { BankaService } from 'src/app/services/banka.service';
import { FilijalaService } from 'src/app/services/filijala.service';

@Component({
  selector: 'app-filijala-dialog',
  templateUrl: './filijala-dialog.component.html',
  styleUrls: ['./filijala-dialog.component.css']
})
export class FilijalaDialogComponent {

  public flag!: number;
  public banke!: Banka[];
  private subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<FilijalaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Filijala,
    public filijalaService: FilijalaService,
    public bankaService: BankaService) { }

  ngOnInit() {
    this.subscription = this.bankaService.getAllBanka().subscribe(data => { this.banke = data });
  }

  compareTo(a: any, b: any) {
    return a.id == b.id;
  }

  public add(): void {
    this.filijalaService.addFilijala(this.data).subscribe(() => {
      this.snackBar.open('Uspesno dodata filijala: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove filijale. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.filijalaService.updateFilijala(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena filijala: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene filijale. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.filijalaService.deleteFilijala(this.data.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana filijala: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja filijale. ', 'Zatvori', {
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
