import { Component, OnInit } from '@angular/core';
import { BookService } from './service/book/book.service';
// import { APPLICATION_STATE } from './globals';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  constructor(private bookService: BookService) {}
  title = 'ProjectAurora';

  ngOnInit() {
    // this.bookService.getAllBooks().subscribe(
    //   (data) => {
    //     // Handle the data received from the API
    //     console.log(data);
    //   },
    //   (error) => {
    //     // Handle errors
    //     console.error(error);
    //   }
    // );
  }
}
