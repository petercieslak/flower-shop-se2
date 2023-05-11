import React from 'react'

export const TokenContext = React.createContext({
    token: "",
    setToken: (token) => {}
  });
export const NameContext = React.createContext({
    name: "",
    setName: (name) => {}
  });
export const TypeContext = React.createContext({
    type: "",
    setType: (type) => {}
  });