import { Directive, HostListener } from '@angular/core';

@Directive({
  selector: '[appOnlyNumbers]',
})
export class OnlyNumbersDirective {
  @HostListener('input', ['$event']) onInputChange(event: {
    target: { value: any };
    stopPropagation: () => void;
  }) {
    const initalValue = event.target.value;
    event.target.value = initalValue.replace(/[^0-9]*/g, '');
    if (initalValue !== event.target.value) {
      event.stopPropagation();
    }
  }
}
