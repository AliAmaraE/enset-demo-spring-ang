import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit{
  public payments : any;
  public dataSource : any;
  public dispalyedColumns=['id','date','amount','type','status','firstname'];
  constructor(private http:HttpClient) {

  }
  ngOnInit() {
    this.http.get("http://localhost:8021/payments")
      .subscribe({
        next:value=>{
          this.payments=data;
          this.dataSource = new MatTableDataSource(this.payments);

        },
        error : err =>{
          console.log(err);
        }
      })
  }

}
