import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KORISNIKUSLUGA_URL } from '../app.constants';
import { KorisnikUsluga } from '../models/korisnikUsluga';

@Injectable({
  providedIn: 'root'
})
export class KorisnikUslugaService {

  constructor(private httpClient: HttpClient) { }

  public getAllKorisnikUsluga(): Observable<any>{
    return this.httpClient.get(KORISNIKUSLUGA_URL);
  }

  public addKorisnikUsluga(korisnikUsluga:KorisnikUsluga):Observable<any> {
    return this.httpClient.post(KORISNIKUSLUGA_URL,korisnikUsluga);
  }

  public updateKorisnikUsluga(korisnikUsluga:KorisnikUsluga):Observable<any>{
    return this.httpClient.put(KORISNIKUSLUGA_URL+"/"+korisnikUsluga.id,korisnikUsluga);
  }

  public deleteKorisnikUsluga(korisnikUslugaID:number):Observable<any>{
    return this.httpClient.delete(KORISNIKUSLUGA_URL+"/"+korisnikUslugaID);
  }
}
