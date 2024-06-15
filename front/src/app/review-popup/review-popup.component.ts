import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-review-popup',
  templateUrl: './review-popup.component.html',
  styleUrls: ['./review-popup.component.css'],
})
export class ReviewPopupComponent {
  stars: number = 0;
  hoverStars: number = 0;
  starsArray: number[] = [1, 2, 3, 4, 5];
  title: string = '';
  description: string = '';

  constructor(
    public dialogRef: MatDialogRef<ReviewPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  rate(star: number): void {
    this.stars = star;
  }

  hoverRate(star: number): void {
    this.hoverStars = star;
  }

  resetRate(): void {
    this.hoverStars = 0;
  }

  submitReview(): void {
    this.dialogRef.close({
      stars: this.stars,
      title: this.title,
      description: this.description,
    });
  }

  closePopup(): void {
    this.dialogRef.close();
  }
}
