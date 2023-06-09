import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ILoginUser } from '../interface/loginUser.interface';
import { IUser } from '../interface/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url: string = 'http://localhost:8080/store/users';
  private urlLogin: string = 'http://localhost:8080/store/users/login';
  //private urlMockoon: string = 'http://localhost:3013/store/users/';
  //private urlMockoonLogin: string = 'http://localhost:3014/store/login'



  constructor(private httpCliente: HttpClient ) { }

  public insertUser(user: IUser): Observable<IUser>{ return this.httpCliente.post<IUser>(this.url, user); }
  public logintUser(creds: ILoginUser){ return this.httpCliente.post<IUser>(this.urlLogin, creds); }
  public insertFavoriteByUserNameAndItemId(userName: string, itemId: number) {
    let urlFavorite: string = "http://localhost:8080/store/users/" + userName + "/favorites/" + itemId;
    return this.httpCliente.put(urlFavorite, null); }
  public removeFavoriteByUserNameAndItemId(userName: string, itemId: number) {
    let urlFavorite: string = "http://localhost:8080/store/users/" + userName + "/favorites/remove/" + itemId;
    return this.httpCliente.delete(urlFavorite);
  }
  public getAllFavoritesByUserName(username: string): Observable<number[]> {
    let urlFavorite: string = "http://localhost:8080/store/users/" + username + "/favorites";
    return this.httpCliente.get<number[]>(urlFavorite);  }

}
