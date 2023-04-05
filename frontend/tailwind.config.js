/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        'montserrat': ['Montserrat', 'sans-serif']
      },
      backgroundImage: {
        'leaves-pattern': "url('./src/assets/leaves-bg.png')",
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'test': "url('./src/assets/test.jpg')",
      },

    },
  },
  plugins: [],
}

