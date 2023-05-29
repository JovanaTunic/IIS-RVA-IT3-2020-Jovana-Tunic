import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankaComponent } from './components/banka/banka.component';
import { KorisnikUslugaComponent } from './components/korisnik-usluga/korisnik-usluga.component';
import { FilijalaComponent } from './components/filijala/filijala.component';

const routes: Routes =  [ { path: 'banka', component: BankaComponent },
{ path: 'korisnik-usluga', component: KorisnikUslugaComponent },
{ path: 'filijala', component: FilijalaComponent },
{ path: '', redirectTo: '/banka', pathMatch: 'full'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
