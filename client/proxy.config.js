const PROXY_CONFIG = [
  {
    context: ['/api/**'],
    target: 'http://localhost:8080',
    secure: false,
    logLevel: 'debug',
  }
]

module.exports = PROXY_CONFIG;

//ng serve --proxy-config=proxy.config.js

// PROXY FOR VERCEL
// {
//   "rewrites": [
//     {
//       "source": "/api/:path*",
//       "destination": "https://day39-production.up.railway.app/api/:path*"
//     }
//   ]
// }