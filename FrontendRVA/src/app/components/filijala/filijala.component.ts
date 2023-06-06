import { Component, OnInit, ViewChild, OnChanges} from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { Filijala } from 'src/app/models/filijala';
import { FilijalaService } from 'src/app/services/filijala.service';
import { FilijalaDialogComponent } from '../dialogs/filijala-dialog/filijala-dialog.component';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-filijala',
  templateUrl: './filijala.component.html',
  styleUrls: ['./filijala.component.css']
})
export class FilijalaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'adresa','brojPultova','posedujeSef','banka', 'actions'];
  dataSource!: MatTableDataSource<Filijala>; // varijabla u kojoj je smesten tip podatka filijala
  selektovanaFilijala1!: Filijala;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private filijalaService: FilijalaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }  //pirijavljivanje na tok podataka
  ngOnChanges(): void { this.loadData(); } //odjavljivanje sa toka podataka

  public loadData() {
    this.subscription = this.filijalaService.getAllFilijala().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        //sortiramo po ugnjezdenom obelezju
        this.dataSource.sortingDataAccessor = (row: Filijala, columnName: string): string => {

          console.log(row, columnName);
          if (columnName == "banka") return row.banka.naziv.toLocaleLowerCase();
          var columnValue = row[columnName as keyof Filijala] as unknown as string;
          return columnValue;

        }

        this.dataSource.sort = this.sort;
        //filtriranje po ugnjezdenom obelezju
        this.dataSource.filterPredicate = (data, filter: string) => {
          const accumulator = (currentTerm: any, key: string) => {
            return key === 'banka' ? currentTerm + data.banka.naziv : currentTerm + data[key as keyof Filijala];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };

        this.dataSource.paginator = this.paginator;
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

 //iz htmla prosledjujemo ove podatke dijalogu
 openDialog(flag: number, filijala?: Filijala): void {
  const dialogRef = this.dialog.open(FilijalaDialogComponent, { data: (filijala ? filijala : new Filijala()) });
  dialogRef.componentInstance.flag = flag;
  dialogRef.afterClosed().subscribe(res => {
    if (res === 1) //uspesno
    {
      //ponovo učitaj podatke
      this.loadData();
    }
  })
}

selectRow(row: Filijala) {
  this.selektovanaFilijala1 = row;
}

applyFilter(filterValue: any) {
  filterValue = filterValue.target.value
  filterValue = filterValue.trim();
  filterValue = filterValue.toLocaleLowerCase();
  this.dataSource.filter = filterValue;
}

}
