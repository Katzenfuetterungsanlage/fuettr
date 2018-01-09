import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/mergeMap';

import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  public Time: string;
  private promise: Promise<number>;
  private promiseResult: string;
  public cat = false;
  private lick = 0;
  public navShow = false;

  public constructor(private router: Router, private activatedRoute: ActivatedRoute, private titleService: Title) {
    setInterval(this.refreshTime.bind(this), 100);
    // setInterval(() => {
    //   this.Time = Date.now().toString();
    // }, 1);
  }

  private refreshTime() {
    this.Time = new Date().toLocaleTimeString();
  }
  ngOnInit() {
    this.router.events
      .filter(event => event instanceof NavigationEnd)
      .map(() => this.activatedRoute)
      .map(route => {
        while (route.firstChild) {
          route = route.firstChild;
          return route;
        }
      })
      .filter(route => route.outlet === 'primary')
      .mergeMap(route => route.data)
      .subscribe(event => this.titleService.setTitle(event['title']));
  }

  click() {
    this.lick++;
    if (this.lick > 2) {
      switch (this.getRandomInt(1, 17)) {
        case 1:
          console.log('Wieso klickst du da?');
          break;
        case 2:
          console.log('Da passiert nix...');
          break;
        case 3:
          console.log('Hör auf zu klicken!');
          break;
        case 4:
          console.log('Was machst du da?');
          break;
        case 5:
          console.log('Erwartest du etwas?');
          break;
        case 6:
          console.log('Wieso hast du die Konsole offen?');
          break;
        case 7:
          console.log('Wenn du hier nochmal draufklickst wird weiterhin nichts passieren.');
          break;
        case 8:
          console.log(
            'Für nähere Informationen zur Unnützigkeit dieses Klickens fragen sie ihren Programmierer oder werfen sie ihren Pc beim Fenster hinaus.'
          );
          break;
        case 9:
          console.log('Was erwartest du dir hiervon eigentlich?');
          break;
        case 10:
          console.log('Mach die Konsole wieder zu!');
          break;
        case 11:
          console.log('Ich werde böse, wenn du da nochmal draufklickst...');
          break;
        case 12:
          console.log('Diese Nachricht wird ihnen präsentiert von Füttr - Der Katzenfütterungsanlage für alle!');
          break;
        case 13:
          console.log('Drück nochmal drauf, vielleicht passiert wieder nichts...');
          break;
        case 14:
          console.log('Wer das liest ist doof.');
          break;
        case 15:
          console.log('Herzlichen Glückwunsch! Sie haben einmal unnötig geklickt!');
          break;
        case 16:
          console.log('Weiter unnötig herumklicken wird dir auch nichts bringen...');
          break;
        case 17:
          console.log('Bitte weitergehen, hier gibt es nichts zu sehen.');
          break;
        default:
          console.log('Wie das passieren konnte, weis ich auch nicht...');
          break;
      }
    }
    if (this.lick === 69) {
      this.cat = true;
    }
  }

  lic() {
    this.lick = 0;
  }

  back() {
    this.cat = false;
    this.lick = 0;
  }

  toggleState() {
    const bool = this.navShow;
    this.navShow = bool === false ? true : false;
  }

  getRandomInt(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
}
