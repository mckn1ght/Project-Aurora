import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appDoubleNumberInput]',
})
export class DoubleNumberInputDirective {
  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event']) onInputChange(event: Event) {
    const initialValue = this.el.nativeElement.value;
    let newValue = initialValue.replace(/[^0-9.]/g, ''); // Allow only numbers and one dot
    const dotIndex = newValue.indexOf('.');

    if (dotIndex !== -1) {
      // If dot exists, restrict to two decimal places
      const parts = newValue.split('.');
      newValue = parts[0] + '.' + (parts[1] ? parts[1].slice(0, 2) : '');
    }

    this.el.nativeElement.value = newValue;

    if (this.el.nativeElement.value !== initialValue) {
      event.stopPropagation();
    }
  }
}
