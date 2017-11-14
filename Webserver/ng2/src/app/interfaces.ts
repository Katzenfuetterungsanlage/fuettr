export interface Version {
  version: string;
}

export interface Warning {
  warning: string;
}

export interface Warnings {
  warnings: Warning[];
}

export interface Error {
  error: string;
}

export interface Errors {
  errors: Error[];
}
export interface Times {
  time1: string;
  time2: string;
  time3: string;
  time4: string;
}

export interface Status {
  last_time: string;
  next_time: string;
  next_time_in: string;
  machine_state: string;
}

export interface Info {
  serialnumber: string;
  internal: string;
  wlanState: string;
  ipadress: string;
}
