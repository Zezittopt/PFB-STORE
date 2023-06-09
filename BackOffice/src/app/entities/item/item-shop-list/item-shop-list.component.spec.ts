import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemShopListComponent } from './item-shop-list.component';

describe('ItemShopListComponent', () => {
  let component: ItemShopListComponent;
  let fixture: ComponentFixture<ItemShopListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemShopListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemShopListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
