import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { USLUGA_URL, USLUGA_ZA_FILIJALU_URL } from '../app.constants';
import { Usluga } from '../models/usluga';

@Injectable({
  providedIn: 'root'
})
export class UslugaService {

  constructor(private httpClient: HttpClient) { }

  public getAllUslugeZaFilijalu(filId: number): Observable<any> {
    return this.httpClient.get(USLUGA_ZA_FILIJALU_URL+'/'+filId);
  }

  public addUsluga(usluga: Usluga): Observable<any> {
    return this.httpClient.post(USLUGA_URL, usluga);
  }

  public deleteUsluga(id: number): Observable<any> {
    return this.httpClient.delete(USLUGA_URL  + "/" + id);
  }

  public updateUsluga(usluga: Usluga) : Observable<any>{
    return this.httpClient.put(USLUGA_URL + "/" + usluga.id, usluga);  }

}
