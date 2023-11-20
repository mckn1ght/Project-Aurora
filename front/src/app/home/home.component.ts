import { Component } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  domains = [
    {
      name: 'Electrician',
      icon: 'bolt@2x',
      description: '',
      route: 'electricieni',
    },
    {
      name: 'Instalator',
      icon: 'pipe.and.drop@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Tamplar',
      icon: 'tree@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Vopsitor',
      icon: 'paintbrush@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Mecanic',
      icon: 'wrench.and.screwdriver@2x',
      description: '',
      route: 'mecanic',
    },
    {
      name: 'Electronist',
      icon: 'screwdriver@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Frigotehnist',
      icon: 'snowflake@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Tehnician IT',
      icon: 'desktopcomputer.trianglebadge.exclamationmark@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Jardinist',
      icon: 'leaf@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Constructii',
      icon: 'hammer@2x',
      description: '',
      route: 'instalatii',
    },
    {
      name: 'Altele',
      icon: 'square.grid.3x3.middle.filled@2x',
      description: '',
      route: 'instalatii',
    },
  ];
}
