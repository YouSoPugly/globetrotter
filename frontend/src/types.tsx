export type Country = {
  properties: {
    ADMIN: string;
    ISO_A2: string;
    GDP_MD_EST?: number;
    POP_EST?: number;
    [key: string]: any; // for extra fields
  };
  geometry?: any;
};