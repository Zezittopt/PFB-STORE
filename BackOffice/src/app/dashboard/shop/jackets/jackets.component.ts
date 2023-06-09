import { Component, OnInit } from '@angular/core';
import { ItemShop } from 'src/app/entities/item/modelo/itemShop.model';
import { ItemService } from 'src/app/entities/item/service/item.service';

@Component({
  selector: 'app-jackets',
  templateUrl: './jackets.component.html',
  styleUrls: ['./jackets.component.scss']
})
export class JacketsComponent implements OnInit {

  articleList: ItemShop[]=[];
  categoryId: number = 12753;
  constructor( private itemService: ItemService  ){}

  ngOnInit(): void {
    this.getJackets();
  }
  private getJackets() {
    this.itemService.getAllItemsByCategoryId(this.categoryId).subscribe({
      next: (articlesRequest) => {
        articlesRequest.forEach( (article) =>{ //recibo los artículos y a través de la interfaz recupero los datos
          const articleNew: ItemShop = new ItemShop(article.id, article.name, article.price, article.reduced, article.image, article.favorite);
          this.articleList.push(articleNew);
        })
      },
      error: (err) => {this.gestionarError(err);}
    })
  }
  private gestionarError(err: any) {
    console.log(err);
  }
}
