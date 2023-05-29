import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'kontakt','naziv','pib', 'actions'];
  dataSource!: MatTableDataSource<Banka>;

  constructor(private bankaService: BankaService) { }

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

  ngOnDestroy(): void { this.subscription.unsubscribe(); }
}
