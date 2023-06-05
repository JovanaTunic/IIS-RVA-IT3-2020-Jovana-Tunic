import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { KorisnikUsluga } from 'src/app/models/korisnikUsluga';
import { KorisnikUslugaService } from 'src/app/services/korisnikUsluga.service';
import { KorisnikUslugaDialogComponent } from '../dialogs/korisnik-usluga-dialog/korisnik-usluga-dialog.component';


@Component({
  selector: 'app-korisnik-usluga',
  templateUrl: './korisnik-usluga.component.html',
  styleUrls: ['./korisnik-usluga.component.css']
})
export class KorisnikUslugaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'ime','prezime','maticniBroj', 'actions'];
  dataSource!: MatTableDataSource<KorisnikUsluga>;

  constructor(private korisnikUslugaService: KorisnikUslugaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.korisnikUslugaService.getAllKorisnikUsluga().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, korisnikUsluga?: KorisnikUsluga) : void {
    const dialogRef = this.dialog.open(KorisnikUslugaDialogComponent, {data: (korisnikUsluga?korisnikUsluga: new KorisnikUsluga())});
    console.log(dialogRef)
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res==1) this.loadData();}) // u slucaju da je res 1 onda je uspesno dodato
  }

  ngOnDestroy(): void {
     this.subscription.unsubscribe();
  }

  ngOnChanges(){this.loadData();}
}

//ng if kao da smo napravili if uslov
//ng* - direktive
//one way tek kada kliknes ce da preuzme sa html koda
//two way sta si deklarisao u ts fajlu automatski prihgvata informacije
//apl prop konekcija s bazom kako treba da se ponasa, da automatski sql skript popuni bazu
//
