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
    dialogRef.afterClosed().subscribe(res => {if(res==1) this.loadData();})
  }

  ngOnDestroy(): void {
     this.subscription.unsubscribe();
  }

  ngOnChanges(){this.loadData();}
}

