import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';
import { BankaDialogComponent } from '../dialogs/banka-dialog/banka-dialog.component';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'kontakt','naziv','pib', 'actions'];
  dataSource!: MatTableDataSource<Banka>;

  constructor(private bankaService: BankaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.bankaService.getAllBanka().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, banka?: Banka) : void {
    const dialogRef = this.dialog.open(BankaDialogComponent, {data: (banka?banka: new Banka())});
    console.log(dialogRef)
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res==1) this.loadData();})
  }

  ngOnDestroy(): void {
     this.subscription.unsubscribe();
  }

  ngOnChanges(){this.loadData();}
}
