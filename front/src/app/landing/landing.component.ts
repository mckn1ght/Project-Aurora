import { Component, OnInit } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
})
export class LandingComponent implements OnInit {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;
  ngOnInit(): void {}
}
