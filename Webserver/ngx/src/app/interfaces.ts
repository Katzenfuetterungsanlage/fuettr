export interface Version {
  version: string;
}

export interface Error {
  message: string;
  hidden: boolean;
}

export interface Warning {
  message: string;
  hidden: boolean;
}

export interface ErrorsAndWarnings {
  Errors: Error[];
  Warnings: Warning[];
}

export interface AckWarn {
  id: number;
}

export interface AckErr {
  id: number;
}

export interface Times {
  _id: string;
  identifier: string;
  time1: string;
  time1_active: boolean;
  time2: string;
  time2_active: boolean;
  time3: string;
  time3_active: boolean;
  time4: string;
  time4_active: boolean;
}

export interface Status {
  _id: string;
  identifier: string;
  lastFeeding: string;
  nextFeeding: string;
  nextFeedingIn: string;
  machineState: boolean;
}

export interface Info {
  _id: string;
  identifier: string;
  serialnumber: string;
  internal: string;
  wlanState: string;
}

export interface Ip {
  ip: string;
}

export interface Positions {
  _id: string;
  identifier: string;
  motor1: string;
  motor2: string;
  sensor1: string;
  sensor2: string;
}

export interface User {
  name: string;
  password: string;
}

export interface Login {
  isLoggedIn: boolean;
  token: string;
}
