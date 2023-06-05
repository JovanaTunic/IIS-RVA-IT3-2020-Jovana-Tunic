import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Filijala } from 'src/app/models/filijala';
import { Usluga } from 'src/app/models/usluga';
import { UslugaService } from 'src/app/services/usluga.service';
import { UslugaDialogComponent } from '../dialogs/usluga-dialog/usluga-dialog.component';

@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css']
})
export class UslugaComponent implements OnInit {

  displayedColumns = ['id', 'datumUgovora', 'naziv', 'opisUsluge', 'provizija', 'korisnikUsluga', 'filijala', 'actions'];
  dataSource!: MatTableDataSource<Usluga>;
  subscription!: Subscription;
  @Input() selektovanaFilijala!: Filijala;


  constructor(private uslugaService: UslugaService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar) { }

    ngOnDestroy(): void {
      this.subscription.unsubscribe();
    }

    ngOnInit(): void {
      this.loadData();
    }

    ngOnChanges(): void {
      if (this.selektovanaFilijala.id) {
        this.loadData();
      }
    }

    loadData() {
      this.subscription = this.uslugaService.getAllUslugeZaFilijalu(this.selektovanaFilijala.id)
        .subscribe({
          next: (data) => this.dataSource = data,
          error: (error) => {
            this.snackBar.open('Filijala nema usluga', 'Zatvori', {
              duration: 2500
            }); this.dataSource = new MatTableDataSource<Usluga>
          },
          complete: () => console.info('complete')
        })
    }
    public openDialog(flag: number, usluga?: Usluga) {
      const dialogRef = this.dialog.open(UslugaDialogComponent, { data: (usluga ? usluga : new Usluga()) });
      dialogRef.componentInstance.flag = flag;
      if (flag === 1) {
        dialogRef.componentInstance.data.filijala = this.selektovanaFilijala;
      }
      dialogRef.afterClosed()
        .subscribe(result => {
          if (result === 1) {
            this.loadData();
          }
        })
    }


/*applyFilter(filterValue: any) {
  filterValue = filterValue.target.value
  filterValue = filterValue.trim();
  filterValue = filterValue.toLocaleLowerCase();
  this.dataSource.filter = filterValue;
}*/
  }
