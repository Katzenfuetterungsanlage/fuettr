import { Injectable } from '@angular/core';

@Injectable()
export class TimeCalculator {
  toMinutes(time: string): number {
    let timeHours: number;
    let timeMinutes: number;
    if (time[0] === '-' && time[1] === '-' && time[3] === '-' && time[4] === '-') {
      return null;
    }
    timeHours = parseInt(time[0], 10) * 10;
    timeHours = timeHours + parseInt(time[1], 10);
    timeMinutes = parseInt(time[3], 10) * 10;
    timeMinutes = timeMinutes + parseInt(time[4], 10);
    return timeHours * 60 + timeMinutes;
  }

  isValid(time: string): boolean {
    let timeHours = parseInt(time[0], 10) * 10;
    timeHours = timeHours + parseInt(time[1], 10);
    let timeMinutes = parseInt(time[3], 10) * 10;
    timeMinutes = timeMinutes + parseInt(time[4], 10);
    if (time[0] === '-' && time[1] === '-' && time[3] === '-' && time[4] === '-') {
      return false;
    }
    if (timeHours < 24) {
      if (timeMinutes < 60) {
        return true;
      }
    }
    return false;
  }
}
