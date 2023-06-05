import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FILIJALA_URL } from '../app.constants';
import { Filijala } from '../models/filijala';

@Injectable({
  providedIn: 'root'
})
export class FilijalaService {

  constructor(private httpClient: HttpClient) { }

  public getAllFilijala(): Observable<any> {
    return this.httpClient.get(`${FILIJALA_URL}`);
  }

  public addFilijala(filijala: Filijala): Observable<any> {
    return this.httpClient.post(FILIJALA_URL, filijala);
  }

  public deleteFilijala(id: number): Observable<any> {
    return this.httpClient.delete(FILIJALA_URL  + "/" + id);
  }

  public updateFilijala(filijala: Filijala) : Observable<any>{
    return this.httpClient.put(FILIJALA_URL + "/" + filijala.id, filijala);  }
}
