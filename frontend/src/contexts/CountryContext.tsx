import { createContext, useContext, useState, type ReactNode } from "react";
import { type Country } from "../types.tsx";

interface CountryContextType {
    selectD: Country | null;
    setSelectD: React.Dispatch<React.SetStateAction<Country | null>>;
}

const CountryContext = createContext<CountryContextType | undefined>(undefined);

export const CountryProvider = ({ children }: { children: ReactNode }) => {
    const [selectD, setSelectD] = useState<Country | null>(null);

    return (
        <CountryContext.Provider value={{ selectD, setSelectD }}>
            {children}
        </CountryContext.Provider>
    );
};

// eslint-disable-next-line react-refresh/only-export-components
export const useCountry = () => {
    const context = useContext(CountryContext);
    if (!context) throw new Error("useCountry must be used within a CountryProvider");
    return context;
};