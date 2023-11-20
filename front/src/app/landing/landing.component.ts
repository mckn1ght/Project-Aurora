import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { APPLICATION_ENVIRONMENT, APPLICATION_VERSION } from '../constants';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
})
export class LandingComponent implements OnInit {
  appEnvironment: String = APPLICATION_ENVIRONMENT;
  appVersion: String = APPLICATION_VERSION;

  @ViewChild('videoElement', { static: false }) videoElement:
    | ElementRef
    | undefined;

  videoSources: string[] = [
    'assets/videos/landing1.mp4',
    'assets/videos/landing2.mp4',
    'assets/videos/landing3.mp4',
    'assets/videos/landing4.mp4',
    'assets/videos/landing5.mp4',
    'assets/videos/landing6.mp4',
    'assets/videos/landing7.mp4',
    'assets/videos/landing8.mp4',
    'assets/videos/landing9.mp4',
  ];

  currentVideoIndex: number = 0;

  get currentVideoSource(): string {
    return this.videoSources[this.currentVideoIndex];
  }

  ngOnInit(): void {
    // Delay adding the event listener until the next tick
    setTimeout(() => {
      this.addVideoEventListener();
      this.playNextVideo();
    });
  }

  addVideoEventListener(): void {
    if (this.videoElement && this.videoElement.nativeElement) {
      this.videoElement.nativeElement.addEventListener('timeupdate', () => {
        const currentTime = this.videoElement?.nativeElement.currentTime;
        const duration = this.videoElement?.nativeElement.duration;

        // Check if we are close to the end of the video
        if (duration && currentTime && duration - currentTime < 1) {
          this.playNextVideo();
        }
      });
    }
  }

  playNextVideo(): void {
    if (this.videoElement && this.videoElement.nativeElement) {
      let newIndex;
      do {
        newIndex = Math.floor(Math.random() * this.videoSources.length);
      } while (newIndex === this.currentVideoIndex);

      this.currentVideoIndex = newIndex;

      this.videoElement.nativeElement.load();
      this.videoElement.nativeElement.play();
    }
  }
}
