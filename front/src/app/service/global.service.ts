import { Injectable } from '@angular/core';
// import { APPLICATION_STATE } from '../globals';

@Injectable({
  providedIn: 'root',
})
export class GlobalService {
  constructor() {}
  APPLICATION_STATE: string = '';
  setAppState(newState: string) {
    this.APPLICATION_STATE = newState;
  }
}
