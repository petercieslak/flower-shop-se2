import React from 'react'

export const TokenContext = React.createContext({
    token: "",
    setToken: (token) => {}
  });
export const NameContext = React.createContext({
    name: "",
    setName: (name) => {}
  });