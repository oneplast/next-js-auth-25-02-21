import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import { cookies } from "next/headers";
import ClientLayout from "./ClientLayout";
import "./globals.css";
import { parseAccessToken } from "@/lib/auth/token";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Create Next App",
  description: "Generated by create next app",
};

export default async function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  const cookieStore = await cookies();

  const accessToken = cookieStore.get("accessToken")?.value;

  const { me } = parseAccessToken(accessToken);

  return (
    <html lang="ko">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased flex flex-col min-h-[100dvh] border-[5px]`}
      >
        <ClientLayout me={me}>{children}</ClientLayout>
      </body>
    </html>
  );
}
