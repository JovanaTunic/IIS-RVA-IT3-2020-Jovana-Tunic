import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BankaComponent } from './components/banka/banka.component';
import { FilijalaComponent } from './components/filijala/filijala.component';
import { UslugaComponent } from './components/usluga/usluga.component';
import { KorisnikUslugaComponent } from './components/korisnik-usluga/korisnik-usluga.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import {MatIconModule} from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material/table';
import { BankaDialogComponent } from './components/dialogs/banka-dialog/banka-dialog.component';
//import { ServiceWorkerModule } from '@angular/service-worker';import { MatToolbarModule } from '@angular/material/toolbar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    BankaComponent,
    FilijalaComponent,
    UslugaComponent,
    KorisnikUslugaComponent,
    BankaDialogComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatGridListModule,
    MatExpansionModule,
    HttpClientModule,
    MatTableModule,
    MatToolbarModule,
    MatToolbarModule,
    MatDialogModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
